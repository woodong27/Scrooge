import { createStore } from "@reduxjs/toolkit";

const initialState = {
  globalToken: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case "SET_TOKEN_STRING":
      return { ...state, globalToken: action.payload };
    default:
      return state;
  }
};

const store = createStore(reducer);

export default store;
