TimeMachine
======
## 描述
功能: 时间按pattern替换

1.
Pattern

\#{yyyy-MM-dd} // 根据pattern替换

\#{yyyy-MM-dd - 1day} // 根据pattern和偏移量计算, 下同:

\#{yyyy-MM-dd + 1week}

\#{yyyy-MM-dd + 1month}

\#{yyyy-MM-dd - 1year}

2.
Command

<time machine> 1.0
Usage: timemachine [options]

  -f, --file <value>     the path of the file
  -c, --content <value>  pure string content
  -d, --date <value>     init datetime yyyy-MM-dd
  --help                 prints the usage text

Author: zcfrank1st
