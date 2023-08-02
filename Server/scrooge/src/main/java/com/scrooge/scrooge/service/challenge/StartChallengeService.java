package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.dto.challengeDto.ChallengeStartRespDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeRespDto;
import com.scrooge.scrooge.repository.challenge.ChallengeAuthRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeAuthRepository challengeAuthRepository;

    // 사용자가 참여한 시작된 챌린지에 대한 정보 조회
    public MyChallengeRespDto getMyStartedChallenge(Long challengeId) throws IllegalAccessException {

        Optional<Challenge> challenge = challengeRepository.findById(challengeId);

        if(challenge.isPresent()) {
            MyChallengeRespDto myChallengeRespDto = new MyChallengeRespDto();
            myChallengeRespDto.setId(challenge.get().getId());
            myChallengeRespDto.setMainImgAddress(challenge.get().getChallengeExampleImageList().get(0).getImgAddress());
            myChallengeRespDto.setStartDate(challenge.get().getStartDate());
            myChallengeRespDto.setEndDate(challenge.get().getEndDate());
            myChallengeRespDto.setPeriod(challenge.get().getPeriod());

            /* 대전 현황 구현 위해 각 팀의 성공 횟수 가져오기 */
            Integer teamZeroSuccessCount, teamOneSuccessCount;
            teamZeroSuccessCount = challengeAuthRepository.countZeroSuccessCount(challengeId);
            teamOneSuccessCount = challengeAuthRepository.countOneSuccessCount(challengeId);

            myChallengeRespDto.setTeamZeroSuccessCount(teamZeroSuccessCount);
            myChallengeRespDto.setTeamOneSuccessCount(teamOneSuccessCount);

            return myChallengeRespDto;
        }else {
            throw new IllegalAccessException("Challenge not found with Id : " + challengeId);
        }

    }
}
