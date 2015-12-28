<%--
  Created by tom
  User: tom
  Date: 2015/12/23 0023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <script src="http://fb.me/react-0.11.2.js"></script>
  <script src="http://fb.me/JSXTransformer-0.11.2.js"></script>
</head>
<body>
<div id="example"></div>
<script type="text/jsx">
  // ** 在这里替换成你的代码 **
  var HelloWorld = React.createClass({
    render: function() {
      return (
              <p>
                Hello, <input type="text" placeholder="Your name here" />!
                It is {this.props.date.toTimeString()}
              </p>
      );
    }
  });

  setInterval(function() {
    React.render(
            <HelloWorld date={new Date()} />,
            document.getElementById('example')
    );
  }, 500);

</script>
</body>
</html>
