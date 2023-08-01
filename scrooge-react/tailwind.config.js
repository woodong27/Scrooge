/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      textShadow: {
        default: "2px 2px 4px rgba(0, 0, 0, 0.5)", // 그림자 색상과 위치 설정
      },
    },
  },
  plugins: [],
};
