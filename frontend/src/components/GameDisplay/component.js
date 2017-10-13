import React, { PropTypes } from 'react';
import Cell from '../Cell';

const makeCells = (row) => row.map(({ x, y, isOn }) => (
  <Cell
    key={`${x}-${y}`}
    x={x}
    y={y}
    isOn={isOn}
  />
));

const makeRows = (grid) => grid.map(row => (
  <div className="game--row">
    {makeCells(row)}
  </div>
));

const GameDisplay = (props) => (
  <div className={props.className}>
    {makeRows(props.cells)}
  </div>
);

GameDisplay.propTypes = {
  className: PropTypes.string,
  cells: PropTypes.array.isRequired,
};

export default GameDisplay;
