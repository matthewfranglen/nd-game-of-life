import React from 'react';
import PropTypes from 'prop-types';
import Cell from '../Cell';

const makeCells = row => row.map(({ x, y, isOn }) => (
  <Cell
    key={`${x}:${y}`}
    x={x}
    y={y}
    isOn={isOn}
  />
));

const makeRows = grid => grid.map(row => (
  <div key={`${row[0].x}:${row[0].y}`} className="game--row">
    {makeCells(row)}
  </div>
));

const GameDisplay = props => (
  <div className={props.className}>
    {makeRows(props.cells)}
  </div>
);

GameDisplay.propTypes = {
  className: PropTypes.string,
  cells: PropTypes.arrayOf(PropTypes.arrayOf(PropTypes.object)).isRequired,
};
GameDisplay.defaultProps = {
  className: undefined,
};

export default GameDisplay;
