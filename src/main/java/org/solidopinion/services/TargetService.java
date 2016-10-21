package org.solidopinion.services;

import java.io.IOException;

public interface TargetService {
    public void read(String path) throws IOException;

    public String find(String targets);
}
