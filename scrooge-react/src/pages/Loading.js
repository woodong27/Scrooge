import styles from "./Loading.module.css";
import ButtonGreen from "../components/UI/ButtonGreen";

const Loading = (props) => {
  return (
    <div className={styles.body}>
      <div className={styles.main}>
        <div className={styles.title_border}>
          <div className={styles.title}>
            <div>더 나은</div>
            <div>소비습관을</div>
            <div style={{ color: "#A2D660" }}>스쿠루지와</div>
            <div>함께</div>
          </div>
        </div>
        <div className={styles.sub_title}>
          Better consumption habits <br />
          with Scrooge
        </div>
      </div>
      <div className={styles.foot}>
        <img
          src={`${process.env.PUBLIC_URL}/images/loading-cats.png`}
          alt="고양이들"
        />
        <ButtonGreen text="지금 시작하기" onClick={props.onLogin}></ButtonGreen>
      </div>
    </div>
  );
};

export default Loading;
