import { createStore } from "@reduxjs/toolkit";

const initialState = {
  globalToken: "",
  memberId: 0,
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_TOKEN_STRING":
      return { ...state, globalToken: action.payload };
    case "SET_ID_STRING":
      return { ...state, memberId: action.payload };
    default:
      return state;
  }
};

const store = createStore(reducer);

export default store;
