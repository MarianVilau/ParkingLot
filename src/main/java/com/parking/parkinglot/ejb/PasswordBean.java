package com.parking.parkinglot.ejb;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Stateless
public class PasswordBean {
    private static final Logger LOG = Logger.getLogger(PasswordBean.class.getName());
    public String convertToSha256(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                final String hex = Integer.toHexString(0xff & digest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOG.log(Level.SEVERE, "Could not convert password", ex);
        }
        return null;
    }
}


/*Artifact Gradle : com.parking : ParkingLot-1.0-SNAPSHOT.war: java.io.IOException: com.sun.enterprise.admin.remote.RemoteFailureException: Error occurred during deployment: Exception while loading the app : CDI deployment failure:WELD-001408: Unsatisfied dependencies for type PasswordBean with qualifiers @Default at injection point [BackedAnnotatedField] @Inject private com.parking.parkinglot.ejb.UsersBean.passwordBean at com.parking.parkinglot.ejb.UsersBean.passwordBean(UsersBean.java:0) . Please see server.log for more details.


The error message indicates that the CDI (Contexts and Dependency Injection) container is unable to inject a `PasswordBean` instance into the `UsersBean` class. This is because it cannot find a bean of type `PasswordBean` that satisfies the injection point.

Here are a few things you can check:

1. Ensure that `PasswordBean` is annotated with a CDI scope annotation such as `@ApplicationScoped`, `@RequestScoped`, `@SessionScoped`, etc. If it's not, the CDI container won't manage it and therefore won't be able to inject it.

```java
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PasswordBean {
    // existing code...
}
```

2. Make sure that `PasswordBean` is in a package that is scanned by CDI. By default, CDI scans all classes in the `WEB-INF/classes` directory and all classes in jar files located in the `WEB-INF/lib` directory that have a `beans.xml` file in their `META-INF` directory.


 */