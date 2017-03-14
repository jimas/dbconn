package com.jimas.dbconn.api;

import com.jimas.dbconn.pojo.rest.LogPojo;
import com.jimas.dbconn.pojo.rest.ResultVo;

public interface LogApi {
    
    public ResultVo<LogPojo> insertLog(LogPojo logPojo);
}
