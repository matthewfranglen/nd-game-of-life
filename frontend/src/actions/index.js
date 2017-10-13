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

export function receiveMove(cells) {
  return { type: FETCH_MOVE_SUCCESS, cells };
}

export function fetchMove(cells) {
  return (dispatch) => {
    dispatch(requestMove());

    const liveCells = cells
      .map(row => row.filter(({ isOn }) => isOn))
      .reduce((acc, val) => acc.concat(val), [])
      .map(({ x, y }) => [x, y]);

    const data = new FormData();
    data.append('json', JSON.stringify(liveCells));

    return fetch('/next', { method: 'POST', body: data })
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
