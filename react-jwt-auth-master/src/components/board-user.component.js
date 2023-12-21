import React, { Component } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import StockOverviewWithSearch from "../stock/StockOverviewWithSearch";

export default class BoardUser extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
    };

    this.parameterValue = this.props.parameter.username;
  }

  componentDidMount() {
    UserService.getUserBoard().then(
      (response) => {
        this.setState({
          content: response.data,
        });
      },
      (error) => {
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString(),
        });

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }
  //parameterValue = "mgaous";
  //parameterValue = props.parameter.username;
  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>{this.state.content}</h3>
        </header>
        <StockOverviewWithSearch parameter={this.parameterValue} />
      </div>
    );
  }
}
