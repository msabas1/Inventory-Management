import { Link } from "react-router-dom";
import ItemCard from "../../components/itemCard/ItemCard";
import styles from "./Items.module.css";

const Items = (props) => {
  if (props.items.length === 0)
    return (
      <main>
        <h2>
          There are no items.
          <Link to="/items/item/new" className="linkTag">Add Item
          </Link>
        </h2>
      </main>
    )

    return(
      <>
      <main className={styles.itemMain}>
      <Link to="/items/item/add" className="linkTag">Add Item</Link>
        <table className={styles.itemTable}>
          <thead>
            <tr>
              <th>Select</th>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Warehouse</th>
            </tr>
          </thead>
          <tbody>
            {props.items.map(item => (
              <ItemCard key={item.itemId} item={item}/>
            ))}
          </tbody>
        </table>
      </main>
    </>
  )
}

export default Items;