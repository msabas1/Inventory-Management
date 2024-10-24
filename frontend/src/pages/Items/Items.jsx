import { Link } from "react-router-dom";
import ItemCard from "../../components/itemCard/ItemCard";
import styles from "./Items.module.css";
import { useState,useEffect } from "react";

const Items = ({items}) => {
  const [sortedItems, setSortedItems] = useState([]);

  useEffect(() => {
    setSortedItems([...items]);
  }, [items]);

  const handleSort = (evt) => {
    const {value} = evt.target
    if(value == "itemName"){
      setSortedItems([...sortedItems].sort((a, b) => a.name.localeCompare(b.name)));
    }
    if(value == "price"){
      setSortedItems([...sortedItems].sort((a,b) => a.price - b.price));
    }
    if(value == "quantity"){
      setSortedItems([...sortedItems].sort((a, b) => a.quantity - b.quantity));
    }
  }

  if (items.length === 0)
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
      <div className="selectContainer">
      <Link to="/items/item/add" className="linkTag">Add Item</Link>
          <select name="sort" id="sort" onChange={handleSort} className={styles.itemTable}>
            <option value="sortBy">Sort By:</option>
            <option value="itemName">Name</option>
            <option value="price">Price</option>
            <option value="quantity">Quantity</option>
          </select>
      </div>
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
            {sortedItems.map(item => (
              <ItemCard key={item.itemId} item={item}/>
            ))}
          </tbody>
        </table>
      </main>
    </>
  )
}

export default Items;