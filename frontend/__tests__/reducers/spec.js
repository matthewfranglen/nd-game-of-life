import {
  TURN_CELL_ON,
  TURN_CELL_OFF,
} from '../../src/constants';
import createReducer from '../../src/reducers';

describe('reducers', () => {
  it('should return the initial state', () => {
    const initialState = {};
    const result = createReducer(initialState)(undefined, {});

    expect(result).toEqual(initialState);
  });

  it('should handle TURN_CELL_ON', () => {
    const x = 1;
    const y = 0;
    const initialState = { cells: [[{}, { isOn: false, x, y }]] };
    const expected = { cells: [[{}, { isOn: true, x, y }]] };
    const result = createReducer(initialState)(undefined, { type: TURN_CELL_ON, x, y });

    expect(result).toEqual(expected);
  });

  it('should handle TURN_CELL_OFF', () => {
    const x = 1;
    const y = 0;
    const initialState = { cells: [[{}, { isOn: true, x, y }]] };
    const expected = { cells: [[{}, { isOn: false, x, y }]] };
    const result = createReducer(initialState)(undefined, { type: TURN_CELL_OFF, x, y });

    expect(result).toEqual(expected);
  });
});
