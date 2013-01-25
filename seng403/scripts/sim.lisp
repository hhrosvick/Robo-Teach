(let (
       (trace-tags "info5,warning,msg,iRobot,-boundSymbols,-policies9,-commitments,-eventqueue,-conversations")
       (trace-code 10) ; bit 1=off, 2=on, 4=to-monitor-window, 8=trace-to-file
       (sleep-time 2) ; time to sleep between starting dependent agents, adjust for slower machines
       )
  
  ;; Set the options for the agent running the commandline
  (agent.options :options.tracing T)
  (agent.options :options.tracetags trace-tags)
  
  (sleep 2)
  
  (agent.new-agent "iRobotCreate.simulator.Environment" "RoomEnvironment" 5780 :LAC 9000 :process "CURRENT" :trace trace-code :traceTags trace-tags :markup "KQML")
  (sleep 5)

  (agent.tell ":5780" "(iRobot-env.puck :name \"puck\")")
  (agent.tell ":5780" "(iRobot-env.set \"puck\" :labeled NIL)")
  (agent.tell ":5780" "(iRobot-env.circle \"puck\" :color-name \"red\")")
  
  (sleep-ignoring-interrupts 2)
  (agent.new-agent "cpsc403.MyRobot" "Alice2" 9100  :LAC 9000 :process "CURRENT" :trace trace-code 
          :traceTags trace-tags :markup "KQML" :outstream "Alice.out" :instream "Alice.in")
  (agent.tell ":5780" "(iRobot-env.triangle \"Alice2\" :name \"red-tri\" :color-name \"purple\")")
  (agent.new-agent "cpsc403.MyRobot" "Bob2"   9101  :LAC 9000 :process "CURRENT" :trace trace-code 
          :traceTags trace-tags :markup "KQML" :outstream "Bob.out" :instream "Bob.in")
  (agent.tell ":5780" "(iRobot-env.triangle \"Bob2\" :name \"green-tri\" :color-name \"green\")")
  ) ;let