<%@page contentType="text/html" pageEncoding="UTF-8" %>

    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thank You</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .thank-you-container {
                margin-top: 50px;
                text-align: center;
            }

            .thank-you-image {
                width: 150px;
                margin-bottom: 20px;
            }

            .thank-you-buttons a {
                margin: 10px;
            }

            .fade-in {
                animation: fadeIn 2s;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                }

                to {
                    opacity: 1;
                }
            }
        </style>
    </head>

    <body>
        <div class="container thank-you-container fade-in">
            <div class="row">
                <div class="col-md-12">
                    <img src="https://example.com/thank-you-image.png" alt="Thank You" class="thank-you-image">
                    <div class="alert alert-success">
                        <h1 class="text-center">Thank You</h1>
                        <h2 class="text-center">Your order has been placed successfully.</h2>
                    </div>
                    <div class="thank-you-buttons">
                        <a href="/" class="btn btn-primary">Back to Home</a>
                        <a href="/orders" class="btn btn-secondary">View Order</a>
                        <a href="/shop" class="btn btn-success">Continue Shopping</a>
                    </div>
                </div>
            </div>
        </div>
    </body>

    </html>