package org.example.Exceptii;

import java.io.IOException;

public class InvalidCatalogException extends IOException {
    public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }
}
