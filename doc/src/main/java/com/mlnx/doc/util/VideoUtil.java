package com.mlnx.doc.util;

import java.util.ArrayList;
import java.util.List;

import com.mlnx.doc.video.IpCamera;
import com.mlnx.doc.video.MedicalServer;


public class VideoUtil {
	
	
	  public static List<Object> getMeetPlace(MedicalServer server, int yyspid) throws Exception
      {
          try
          {
              List<Object> msgdata = new ArrayList<Object>();
//              List<IpCamera> list = SPMeetingBLL.GetAllByYYSPid(yyspid);
              List<IpCamera> list = server.getServer().getCamera(server.getUser(), yyspid);
              if (list != null)
              {
                  Object[] jsonstr = null;
                  for(IpCamera model :list){
	                  jsonstr = new Object[] { model.getIp(), model.getPort(),model.getUser(), model.getPass(), model.getChannel(),"192.168.2.153", "554", "StreamClient" };
	                  msgdata.add(jsonstr);
                  }
              }
              return msgdata;
          }
          catch (Exception e)
          {

              throw new Exception();
          }
      }
}
