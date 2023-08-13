export const tokenReissue = (dispatch) => {
    const refreshTokenUrl = "https://day6scrooge.duckdns.org/api/token/reissue";

        // 기존 refresh Token을 쿠키에서 가져온다

        let refreshToken = null;

        // 1. 쿠키 문자열 가져오기
        const cookieString = document.cookie;

        const cookieArray = cookieString.split(';');

        for(let i = 0; i <cookieArray.length; i++) {
            const cookie = cookieArray[i].trim();
            if(cookie.startsWith('refreshToken=')) {
                refreshToken = cookie.substring("refreshToken=".length, cookie.length);
                break;
            }
        }

        // 2. fetch ,,

        const obj = {
            refreshToken
        }

        const postData = {
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
            },
            body: JSON.stringify(obj),
        }


        fetch(refreshTokenUrl, postData)
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                // console.log(data.accessToken);
                dispatch({ type: "SET_TOKEN_STRING", payload: "Bearer " + data.accessToken });

                // console.log(data.refreshToken);
                setCookie('refreshToken', data.refreshToken, 7);
            })
            .catch((error) => {
                console.log(error);
            });
            
    }

    function calculateExpiration(days) {
        const now = new Date();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now.toUTCString();
      }
    
      // 쿠키 설정 함수
      function setCookie(name, value, days) {
        const expires = calculateExpiration(days);
        document.cookie = `${name}=${value}; expires=${expires}; path=/; SameSite=None; Secure`;
      }

