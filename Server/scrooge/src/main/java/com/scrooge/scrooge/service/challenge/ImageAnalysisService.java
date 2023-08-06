package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import com.scrooge.scrooge.repository.challenge.ChallengeAuthRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeExampleImageRepository;
import lombok.RequiredArgsConstructor;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ImageAnalysisService {

    private final ChallengeExampleImageRepository challengeExampleImageRepository;
    private final ChallengeAuthRepository challengeAuthRepository;

    public double compareImages(String imagePath1, String imagePath2) {
        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePath2);

        Mat hist1 = new Mat();
        Mat hist2 = new Mat();

        Imgproc.cvtColor(image1, image1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, image2, Imgproc.COLOR_BGR2GRAY);

        Imgproc.calcHist(
                Arrays.asList(image1),
                new MatOfInt(0),
                new Mat(),
                hist1,
                new MatOfInt(256),
                new MatOfFloat(0, 256)
        );

        Imgproc.calcHist(
                Arrays.asList(image2),
                new MatOfInt(0),
                new Mat(),
                hist2,
                new MatOfInt(256),
                new MatOfFloat(0, 256)
        );

        return Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
    }
}
