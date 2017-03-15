package com.jimas.dbconn.api;

import com.jimas.common.ResultVo;
import com.jimas.dbconn.pojo.rest.LogPojo;

public interface LogApi {
    
    public ResultVo<LogPojo> insertLog(LogPojo logPojo);
}
