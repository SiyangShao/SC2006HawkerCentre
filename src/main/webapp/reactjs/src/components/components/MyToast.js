import React, {Component} from 'react';
import {Toast} from 'react-bootstrap';

export default class MyToast extends Component{
    render() {
        const toastCss = {
            position: 'fixed',
            top: '70px',
            right:'20px',
            zIndex:'1',
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)'
        };

        return (
            <div style={this.props.children.show ? toastCss : null}>
                <Toast className={"border border-warning bg-warning text-black"}>
                    <Toast.Header className={"bg-warning text-black"} closeButton={false}>
                        <strong className="mr-auto">Error</strong>
                    </Toast.Header>
                    <Toast.Body>
                        {this.props.children.message}
                    </Toast.Body>
                </Toast>
            </div>
        );
    };
}
