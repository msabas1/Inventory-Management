import { Link } from "react-router-dom";
import styles from "./WarehouseCard.module.css";

const WarehouseCard = (props) => {
  return (
    <Link to={`/warehouses/warehouse/${props.warehouse.warehouseId}`} className={styles.warehouseContainer}>
    <p>{props.warehouse.warehouseName}</p>
    <p>Capacity: {props.warehouse.capacity}</p>
  </Link>
  );
}

export default WarehouseCard;