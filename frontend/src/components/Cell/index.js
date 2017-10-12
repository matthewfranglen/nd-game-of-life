import { connect } from 'react-redux';
import Cell from './component';
import { turnCellOn, turnCellOff } from '../../actions';

const mapStateToProps = () => ({});

const mapDispatchToProps = ({ turnCellOn, turnCellOff });

const VisibleCell = connect(
  mapStateToProps,
  mapDispatchToProps,
)(Cell);

export { Cell };
export default VisibleCell;
