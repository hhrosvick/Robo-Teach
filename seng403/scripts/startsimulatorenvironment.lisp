(let (
      (trace-tags "info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations")
      (trace-code 10) ; bit 1=off, 2=on, 4=to-monitor-window, 8=trace-to-file
      (sleep-time 2) ; time to sleep between starting dependent agents, adjust for slower machines
      )
  
  ;; Set the options for the agent running the commandline
  (agent.options :options.tracing T)
  (agent.options :options.tracetags trace-tags)
  
  (agent.new-agent "iRobotCreate.simulator.Environment" 
                   "RoomEnvironment" 
                   5780
                   :LAC 9000 
                   :process "CURRENT" 
                   :trace trace-code 
                   :traceTags trace-tags 
                   :markup "KQML"
                   :interface "none")
  ); let