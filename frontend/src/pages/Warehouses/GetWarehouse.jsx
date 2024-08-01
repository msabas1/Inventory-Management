import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

import * as WarehouseService from '../../services/WarehouseService'

const GetWarehouse = ({handleDeleteWarehouse}) => {
  const {warehouseId} = useParams()
  const [warehouse, setWarehouse] = useState({})

  useEffect(()=> {
    const fetchWarehouse = async () => {
      const data = await WarehouseService.show(warehouseId)
      setWarehouse(data)
    }
    fetchWarehouse()
  }, [warehouseId])

  return ( 
    <main>
      <h1>Warehouse: {warehouse.warehouseName}</h1>
      <h2>Capacity: {warehouse.capacity}</h2>
      <Link to={`/warehouses/warehouse/${warehouse.warehouseId}/edit`} state={warehouse}>Update</Link>
      <p> ----------- </p>
      <Link to={`/warehouses`} onClick={() => handleDeleteWarehouse(warehouseId)}>Delete</Link>
    </main>
   );
}

export default GetWarehouse;