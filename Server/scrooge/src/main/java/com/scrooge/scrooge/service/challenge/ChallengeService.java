package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.config.FileUploadProperties;
import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import com.scrooge.scrooge.dto.challengeDto.ChallengeExampleImageDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeReqDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeRespDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeExampleImageRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeExampleImageRepository challengeExampleImageRepository;
    private final ChallengeParticipantRepository challengeParticipantRepository;

    private final FileUploadProperties fileUploadProperties;

    // 챌린지 생성 API
    @Transactional
    public void createChallenge(ChallengeReqDto challengeReqDto, List<MultipartFile> images) {
        Challenge challenge = new Challenge();

        // 챌린지 정보 저장하기
        challenge.setChallengeMaster(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challenge.setTitle(challengeReqDto.getTitle());
        challenge.setPeriod(challengeReqDto.getPeriod());
        challenge.setCategory(challengeReqDto.getCategory());
        challenge.setMinParticipants(challengeReqDto.getMinParticipants());
        challenge.setMaxParticipants(20);
        challenge.setStatus(0);
        challenge.setAuthMethod(challengeReqDto.getAuthMethod());
        challenge.setDescription(challengeReqDto.getDescription());

        challengeRepository.save(challenge);

        // challengeMaster를 challengeParticipant에 추가하기
        ChallengeParticipant challengeParticipant = new ChallengeParticipant();
        challengeParticipant.setTeam(0);
        challengeParticipant.setMember(memberRepository.findById(challengeReqDto.getChallengeMasterId()).orElse(null));
        challengeParticipant.setChallenge(challenge);
        challengeParticipantRepository.save(challengeParticipant);

        // ChallengeExampleImage에 이미지 5장 저장
        saveChallengeExampleImages(challenge, images);
    }

    // 챌린지에 해당하는 성공 예시 이미지 ChallengeExampleImage에 저장하기
    private void saveChallengeExampleImages(Challenge challenge, List<MultipartFile> images) {

        // 업로드할 위치 설정
        String uploadLocation = fileUploadProperties.getUploadLocation() + "/challenge/examples";

        System.out.println(images.size());

        for(MultipartFile image : images) {
            // 업로드된 사진의 파일명을 랜덤 UUID로 생성
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = null;

            try {
                // 업로드할 위치에 파일 저장
                byte[] bytes = image.getBytes();
                filePath = Paths.get(uploadLocation + File.separator + fileName);
                Files.write(filePath, bytes);

                // ChallengeExampleImage에 이미지를 저장한다.
                ChallengeExampleImage challengeExampleImage = new ChallengeExampleImage();
                challengeExampleImage.setChallenge(challenge);
                challengeExampleImage.setImgAddress(filePath.toString());

                challengeExampleImageRepository.save(challengeExampleImage); //DB에 이미지 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 챌린지 전체를 조회하는 API
    public List<ChallengeRespDto> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        return getChallengeRespDtos(challenges);
    }

    // 카테고리 별 챌린지 전체를 조회하는 API
    public List<ChallengeRespDto> getChallengesbyCategory(Integer categoryId) {
        String category = "";
        switch (categoryId){
            case 1:
                category = "식비";
                break;
            case 2:
                category = "교통비";
                break;
            case 3:
                category = "쇼핑";
                break;
            case 4:
                category = "기타";
                break;
        }

        List<Challenge> challenges = challengeRepository.findAllByCategory(category);
        return getChallengeRespDtos(challenges);
    }

    // 마이 챌린지 조회하는 API
    public List<ChallengeRespDto> getMyChallenges(Long memberId, Integer statusId) {
        List<Challenge> challenges = new ArrayList<>();
        List<ChallengeParticipant> challengeParticipantList = challengeParticipantRepository.findAllByMemberId(memberId);

        // status가 같은 애만 추가해주기
        for(ChallengeParticipant challengeParticipant : challengeParticipantList) {
            if(statusId == challengeParticipant.getChallenge().getStatus()) {
                challenges.add(challengeParticipant.getChallenge());
            }
        }

        return getChallengeRespDtos(challenges);
    }


    private List<ChallengeRespDto> getChallengeRespDtos(List<Challenge> challenges) {
        return challenges.stream()
                .map(challenge -> {
                    ChallengeRespDto challengeRespDto = new ChallengeRespDto();
                    challengeRespDto.setId(challenge.getId());
                    challengeRespDto.setCategory(challenge.getCategory());
                    challengeRespDto.setTitle(challenge.getTitle());
                    challengeRespDto.setCurrentParticipants(challenge.getChallengeParticipantList().size());
                    challengeRespDto.setMinParticipants(challenge.getMinParticipants());
                    challengeRespDto.setMainImgAddress(challenge.getChallengeExampleImageList().get(0).getImgAddress());

                    return challengeRespDto;
                })
                .collect(Collectors.toList());
    }

    // 챌린지 상세 조회 API
    public ChallengeRespDto getChallenge(Long challengeId) throws IllegalAccessException {
        Optional<Challenge> challenge = challengeRepository.findById(challengeId);

        if(challenge.isPresent()) {
            ChallengeRespDto challengeRespDto = new ChallengeRespDto();
            challengeRespDto.setId(challenge.get().getId());
            challengeRespDto.setMainImgAddress(challenge.get().getChallengeExampleImageList().get(0).getImgAddress());
            challengeRespDto.setCategory(challenge.get().getCategory());
            challengeRespDto.setTitle(challenge.get().getTitle());
            challengeRespDto.setPeriod(challenge.get().getPeriod());
            challengeRespDto.setCurrentParticipants(challenge.get().getChallengeParticipantList().size());
            challengeRespDto.setMinParticipants(challenge.get().getMinParticipants());
            challengeRespDto.setAuthMethod(challenge.get().getAuthMethod());
            challengeRespDto.setDescription(challenge.get().getDescription());

            List<ChallengeExampleImage> challengeExampleImageList = challengeExampleImageRepository.findByChallengeId(challengeId);
            challengeRespDto.setChallengeExampleImageList(challengeExampleImageList.stream()
                    .map(ChallengeExampleImageDto::new)
                    .collect(Collectors.toList()));

            return challengeRespDto;
        } else {
            throw new IllegalAccessException("Challenge not found with ID: " + challengeId);
        }
    }

    public void participateInChallenge(Long challengeId, Long memberId) {
        ChallengeParticipant challengeParticipant = new ChallengeParticipant();

        // 해당 challengeId의 challenge의 팀0 인원과 팀1의 인원 비교
        Integer teamZeroMemberCnt = challengeParticipantRepository.countTeamZeroByChallengeId(challengeId);
        Integer teamOneMemberCnt = challengeParticipantRepository.countTeamOneByChallengeId(challengeId);

        Integer team = 0; // 팀 0으로 일단 배정
        if(teamZeroMemberCnt > teamOneMemberCnt) { // 팀 1의 인원이 더 작을 경우 team1에 사용자 넣어주기
            team = 1;
        }

        challengeParticipant.setTeam(team);
        challengeParticipant.setChallenge(challengeRepository.findById(challengeId).orElse(null));
        challengeParticipant.setMember(memberRepository.findById(memberId).orElse(null));

        // challengeParticipant에 참여자 정보 추가하기
        challengeParticipantRepository.save(challengeParticipant);
    }
}