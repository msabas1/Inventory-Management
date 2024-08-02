import { Link } from "react-router-dom";
import styles from "./ItemCard.module.css";

const ItemCard = (props) => {
  return (
    <Link to={`/items/item/${props.item.itemId}`} className={styles.itemContainer}>
    <p>{props.item.name}</p>
    <p>"{props.item.description}"</p>
    <p>Price: ${props.item.price}</p>
    <p>Quantity: {props.item.quantity}</p>
    <p>Located at Warehouse: #{props.item.warehouse}</p>
  </Link>
  );
}

export default ItemCard;