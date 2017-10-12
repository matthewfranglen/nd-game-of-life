import React, { PropTypes } from 'react';
import Cell from '../Cell';

const GameDisplay = (props) => (
  <div className={props.className}>
    {
      props.cells.map(([x, y]) => (
        <Cell
          key={`${x}-${y}`}
          x={x}
          y={y}
          isOn
        />
      ))
    }
  </div>
);

GameDisplay.propTypes = {
  className: PropTypes.string,
  cells: PropTypes.array.isRequired,
};

export default GameDisplay;
