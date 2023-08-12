from fastapi import FastAPI, Depends, Path, HTTPException
from pydantic import BaseModel
import requests
import cv2
from io import BytesIO
import numpy as np

app = FastAPI()

def compareImages(imgURL1, imgURL2):
    image1=requests.get(imgURL1)
    image2=requests.get(imgURL2)
    
    image_data1 = BytesIO(image1.content)
    image_data2 = BytesIO(image2.content)
    
    image_array1 = np.asarray(bytearray(image_data1.read()), dtype=np.uint8)
    image_array2 = np.asarray(bytearray(image_data2.read()), dtype=np.uint8)
    
    image1 = cv2.imdecode(image_array1, cv2.IMREAD_COLOR)
    image2 = cv2.imdecode(image_array2, cv2.IMREAD_COLOR)
    
    if image1 is None or image2 is None:
        return "이미지 불러오기 실패"
    
    gray_image1 = cv2.cvtColor(image1, cv2.COLOR_BGR2GRAY)
    gray_image2 = cv2.cvtColor(image2, cv2.COLOR_BGR2GRAY)
    
    hist1 = cv2.calcHist([gray_image1], [0], None, [256], [0, 256])
    hist2 = cv2.calcHist([gray_image2], [0], None, [256], [0, 256])
    
    similarity = cv2.compareHist(hist1, hist2, cv2.HISTCMP_CORREL)
    
    print(similarity)
    return similarity

class ImagePaths(BaseModel):
    imageURL1: str
    imageURL2: str

@app.post("/compare-images")
async def first_get(imagePaths: ImagePaths):
    return compareImages(imagePaths.imageURL1, imagePaths.imageURL2)