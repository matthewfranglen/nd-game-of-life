import fetch from 'isomorphic-fetch';
import {
  FETCH_MOVE_REQUEST,
  FETCH_MOVE_SUCCESS,
  TURN_CELL_ON,
  TURN_CELL_OFF,
} from '../constants';

export function requestMove() {
  return { type: FETCH_MOVE_REQUEST };
}

export function receiveMove(board) {
  return { type: FETCH_MOVE_SUCCESS, board };
}

export function fetchMove(board) {
  return (dispatch) => {
    dispatch(requestMove());

    return fetch('/next', { data: board })
      .then(response => response.json())
      .then(json => dispatch(receiveMove(json)));
  };
}

export function turnCellOn(x, y) {
  return { type: TURN_CELL_ON, x, y };
}

export function turnCellOff(x, y) {
  return { type: TURN_CELL_OFF, x, y };
}
