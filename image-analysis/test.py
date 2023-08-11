import cv2
import numpy as np
import requests
from io import BytesIO

def compareImages(imgURL1, imgURL2):
    image1=requests.get(imgURL1)
    image2=requests.get(imgURL2)
    
    image_data1 = BytesIO(image1.content)
    image_data2 = BytesIO(image2.content)
    
    # 바이트 데이터를 numpy 배열로 변환
    image_array1 = np.asarray(bytearray(image_data1.read()), dtype=np.uint8)
    image_array2 = np.asarray(bytearray(image_data2.read()), dtype=np.uint8)
    
    # 이미지 불러오기
    image1 = cv2.imdecode(image_array1, cv2.IMREAD_COLOR)
    image2 = cv2.imdecode(image_array2, cv2.IMREAD_COLOR)
    
    if image1 is None or image2 is None:
        return "이미지 불러오기 실패"
    
    # 이미지를 그레이스케일로 변환
    gray_image1 = cv2.cvtColor(image1, cv2.COLOR_BGR2GRAY)
    gray_image2 = cv2.cvtColor(image2, cv2.COLOR_BGR2GRAY)
    
    # 히스토그램 계산
    hist1 = cv2.calcHist([gray_image1], [0], None, [256], [0, 256])
    hist2 = cv2.calcHist([gray_image2], [0], None, [256], [0, 256])
    
    # 히스토그램 비교
    similarity = cv2.compareHist(hist1, hist2, cv2.HISTCMP_CORREL)
    
    return similarity

print(compareImages("https://storage.googleapis.com/scroogestorage/6e72bbbb-1317-404a-b81d-8007e11f6411", "https://storage.googleapis.com/scroogestorage/3276f9de-b309-4501-a261-059ba15deb64"))