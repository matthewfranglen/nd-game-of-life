import { connect } from 'react-redux';
import GameDisplay from './component';

const mapStateToProps = state => ({ cells: state.cells });

const VisibleGameDisplay = connect(mapStateToProps)(GameDisplay);

export { GameDisplay };
export default VisibleGameDisplay;
