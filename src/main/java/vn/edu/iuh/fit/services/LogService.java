package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Log;
import vn.edu.iuh.fit.repositories.LogRespository;

import java.util.List;

public class LogService {
    private static final LogRespository logRespository = new LogRespository();
    public boolean addLog(Log log){
        return logRespository.addLog(log);
    }

    public List<Log> getAll() {
        return logRespository.getAll();
    }
}
