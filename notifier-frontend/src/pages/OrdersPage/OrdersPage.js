import React, {useEffect, useState} from 'react';
import OrdersCard from '../../components/OrdersCard/OrdersCard';



const OrdersPage = () => {
    const [orderList, setOrderList] = useState([]);

    useEffect(() => {
        const eventSource = new EventSource('http://localhost:8081/orders');
        eventSource.onopen = (event) => {
            setOrderList([]);
            // console.log('open', event);
        };
        eventSource.onmessage = (event) => {
            // console.log("received event", new Date().toString());
            const item = JSON.parse(event.data);
            setOrderList((items) => ([...items, item]));
        };
        eventSource.onerror = (event) => {
            // console.log('error', event.eventPhase);
            // eventSource.close();
        }
        return () => {
            eventSource.close();
        }
    }, []);

    return (
        <>
            <div className="card-container">
                <div className="card">
                    {orderList.map((order) => (
                        <OrdersCard
                            key={order.id}
                            orderId={order.id}
                            message={order.message}
                            createdTime={order.createdTime}
                        />
                    ))}
                </div>

            </div>

        </>
    )


}

export default OrdersPage;
