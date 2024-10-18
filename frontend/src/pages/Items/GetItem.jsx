import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import styles from "./Items.module.css";

import * as ItemService from '../../services/ItemService'

const GetItem = ({handleDeleteItem}) => {
  const {itemId} = useParams()
  const [item, setItem] = useState({})

  useEffect(()=> {
    const fetchItem = async () => {
      const data = await ItemService.getItemById(itemId)
      setItem(data)
    }
    fetchItem()
  }, [itemId])

  return ( 
    <main>
      <h1>{item.name}</h1>
      <h2>{item.description}, ${item.price}, Quantity: {item.quantity}</h2>
      <Link to={`/items/item/${item.itemId}/update`} state={item} className="linkTag">Update</Link>
      <Link to={`/items`} className="linkTag" onClick={() => handleDeleteItem(itemId) }>Delete</Link>
    </main>
   );
}

export default GetItem;