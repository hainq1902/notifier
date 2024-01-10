import React, {useEffect, useState} from 'react';
import OrdersCard from '../../components/OrdersCard/OrdersCard';

const OrdersPage = () => {
    const [orderList, setOrderList] = useState([]);
    const [alert, setAlert] = useState(new Audio(require('../../assets/audio/alert.mp3')));

    useEffect(() => {
        const eventSource = new EventSource('http://notifier-backend:32002/orders');
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

    useEffect(() => {
        if (orderList.findIndex((order) => order.status === 'NEW') >= 0) {
            alert.play();
        } else {
            alert.pause();
        }

    }, [orderList]);

    return (
        <>
            <div className="container">
                <div className="card-container">

                    <div><h2> NEW </h2></div>
                    <hr />
                    {orderList
                        .filter((order) => order.status === 'NEW')
                        .map((order) => (
                                <OrdersCard
                                    key={order.id}
                                    orderId={order.id}
                                    message={order.message}
                                    status={order.status}
                                    createdTime={order.createdTime}
                                />))
                    }

                    {
                        (orderList.findIndex((order) => order.status === 'NEW') < 0) &&
                                                <div className='placeholder' />
                    }
                </div>
                <hr className='vertical-separator'/>
                <div className="card-container">
                    <div><h2> DONE </h2></div>
                    <hr />
                    {orderList
                        .filter((order) => order.status === 'DONE')
                        .map((order) => (
                            <OrdersCard
                                key={order.id}
                                orderId={order.id}
                                message={order.message}
                                status={order.status}
                                createdTime={order.createdTime}
                                processedTime={order.processedTime}
                            />))
                    }

                    {
                        (orderList.findIndex((order) => order.status === 'DONE') < 0) &&
                                                <div className='placeholder' />
                    }
                </div>



            </div>
        </>
    )


}

export default OrdersPage;
