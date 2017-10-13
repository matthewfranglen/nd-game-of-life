import R from 'ramda';

import {
  FETCH_MOVE_REQUEST,
  FETCH_MOVE_SUCCESS,
  TURN_CELL_ON,
  TURN_CELL_OFF,
} from '../constants';

const actionHandlers = {};

function setCell(cells, x, y, isOn) {
  const path = R.lensPath([y, x, 'isOn']);

  return R.set(path, isOn, cells);
}

actionHandlers[FETCH_MOVE_REQUEST] = state =>
  Object.assign({}, state, { isFetching: true });

actionHandlers[FETCH_MOVE_SUCCESS] = (state, { cells }) => {
  const grid = Array.from({ length: 10 })
    .map((_outer, y) => Array.from({ length: 10 })
      .map((_inner, x) => ({ isOn: false, x, y })));

  cells.forEach(([x, y]) => {
    grid[y][x].isOn = true;
  });

  return Object.assign({}, state, { isFetching: false, cells: grid });
};

actionHandlers[TURN_CELL_ON] = (state, { x, y }) => {
  const cells = setCell(state.cells, x, y, true);

  return Object.assign({}, state, { cells });
};

actionHandlers[TURN_CELL_OFF] = (state, { x, y }) => {
  const cells = setCell(state.cells, x, y, false);

  return Object.assign({}, state, { cells });
};

export default function createReducer(initialState) {
  return (state = initialState, action) => {
    const reducer = actionHandlers[action.type];
    if (reducer) {
      return reducer(state, action);
    }
    return state;
  };
}
