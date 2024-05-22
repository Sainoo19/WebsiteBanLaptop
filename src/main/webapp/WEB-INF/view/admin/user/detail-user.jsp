<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Detail User</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5 mx-5">
                    <div class="row">
                        <div class="d-flex justify-content-between">
                            <h3>Detail User</h3>
                            <a href="/admin/user" class="btn btn-primary">Back</a>
                        </div>
                        <div class="card" style="width: 25rem;">
                            <div class="card-header">
                                User Information
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Id:${user.id}</li>
                                <li class="list-group-item">Full Name:${user.fullName}</li>
                                <li class="list-group-item">Email:${user.email}</li>

                            </ul>
                        </div>
                    </div>
                </div>
            </body>

            </html>