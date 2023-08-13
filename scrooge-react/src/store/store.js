import { createStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";

const initialState = {
  globalToken: "",
  memberId: 0,
  isLogIn: false
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_TOKEN_STRING":
      return { ...state, globalToken: action.payload };
    case "SET_ID_STRING":
      return { ...state, memberId: action.payload };
    case "SET_LOG_IN":
      return {...state, isLogIn: action.payload};
    default:
      return state;
  }
};

const store = createStore(reducer);

export default store;
