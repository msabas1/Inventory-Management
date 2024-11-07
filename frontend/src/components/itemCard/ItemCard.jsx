import { Link } from "react-router-dom";
import styles from "./ItemCard.module.css";

const ItemCard = (props) => {
  return ( 
    <>
      <tr>
        <td><Link to={`/items/item/${props.item.itemId}`} className="linkTag" id="get-item-link"></Link></td>
        <td>{props.item.name}</td>
        <td>{props.item.description}</td>
        <td>${props.item.price}</td>
        <td>{props.item.quantity}</td>
        <td>Located at Warehouse: #{props.item.warehouse}</td>
      </tr>
    </>
   );
}

export default ItemCard;