import { useState } from "react";

const AddItem = (props) => {
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    price: '',
    quantity: '',
    warehouse: 0,
  })

  const handleChange = (evt) => {
    const { name, value } = evt.target
    setFormData({ ...formData, [name]: value })
  }
  
  const handleSubmit = (evt) => {
    evt.preventDefault()
    props.handleAddItem(formData)
  }

  return ( 
    <>
      <main >
        <h1>Add Item</h1>
        <div>
          <form onSubmit={handleSubmit} id="add-item-form-modal">
          <fieldset>
              <legend>Name</legend>
              <input
                required
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="Enter item name"
                autoComplete="off"
                id="add-item-name-field"
              />
            </fieldset>
          <fieldset>
              <legend>Description</legend>
              <input
                required
                type="text"
                name="description"
                value={formData.description}
                onChange={handleChange}
                placeholder="Enter item description"
                autoComplete="off"
                id="add-item-description-field"
              />
            </fieldset>
          <fieldset>
              <legend>Price</legend>
              <input
                required
                type="text"
                name="price"
                value={formData.price}
                onChange={handleChange}
                placeholder="Enter item price"
                autoComplete="off"
                id="add-item-price-field"
              />
            </fieldset>
          <fieldset>
              <legend>Quantity</legend>
              <input
                required
                type="text"
                name="quantity"
                value={formData.quantity}
                onChange={handleChange}
                placeholder="Enter item quantity"
                autoComplete="off"
                id="add-item-quantity-field"
              />
            </fieldset>
            <fieldset>
                <legend>Warehouse ID</legend>
                <select name="warehouse" onChange={handleChange} id="add-item-warehouseId-field">
                  <option value={formData.warehouseId}>--</option>
                  {props.warehouses.map(warehouse => (
                    <option key={warehouse.warehouseId} value={formData.warehouseId} onChange={handleChange}>{warehouse.warehouseId}</option>
                  ))}
                </select>
              </fieldset>
            <button aria-label="Submit Add Item" type="submit" className="formButton" id="submit-add-item-btn">Submit</button>
          </form>
        </div>
      </main>
    </>
   );
}
 
export default AddItem;