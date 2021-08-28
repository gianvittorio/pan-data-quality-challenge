DROP TABLE IF EXISTS `base_exemplo_score`;
CREATE EXTERNAL TABLE `base_exemplo_score`(
  `identif_mask` bigint,
  `modelo` string,
  `score` bigint,
  `restritivo`bigint,
  `positivo` bigint,
  `msg` string,
  `anomesdia` date)
ROW FORMAT DELIMITED
  FIELDS TERMINATED BY ','
STORED AS INPUTFORMAT
  'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION
  's3://s3-java-bucket-175sl11t5xe2j/outbound/'
  TBLPROPERTIES ("skip.header.line.count"="1");


DROP VIEW IF EXISTS score_histogram;
CREATE VIEW score_histogram AS
SELECT hist.value AS value,
         hist.freq AS frequency,
         avg.value AS average,
         stddev.value AS std_dev
FROM
    (SELECT bes.score AS value,
         count(*) AS freq
    FROM base_exemplo_score AS bes
    GROUP BY  bes.score
    ORDER BY  freq DESC, score) AS hist
CROSS JOIN
    (SELECT round(avg(score), 2) AS value
    FROM base_exemplo_score) AS avg
CROSS JOIN
    (SELECT round(stddev(score), 2) AS value
    FROM base_exemplo_score) AS stddev;


DROP VIEW IF EXISTS restritivo_histogram;
CREATE VIEW restritivo_histogram AS
SELECT hist.value AS value,
         hist.freq AS frequency,
         avg.value AS average,
         stddev.value AS std_dev
FROM
    (SELECT bes.restritivo AS value,
         count(*) AS freq
    FROM base_exemplo_score AS bes
    GROUP BY  bes.restritivo
    ORDER BY  freq DESC, restritivo) AS hist
CROSS JOIN
    (SELECT round(avg(restritivo), 2) AS value
    FROM base_exemplo_score) AS avg
CROSS JOIN
    (SELECT round(stddev(restritivo), 2) AS value
    FROM base_exemplo_score) AS stddev;


DROP VIEW IF EXISTS positivo_histogram;
CREATE VIEW positivo_histogram AS
SELECT hist.value AS value,
         hist.freq AS frequency,
         avg.value AS average,
         stddev.value AS std_dev
FROM
    (SELECT bes.positivo AS value,
         count(*) AS freq
    FROM base_exemplo_score AS bes
    GROUP BY  bes.positivo
    ORDER BY  freq DESC, positivo) AS hist
CROSS JOIN
    (SELECT round(avg(positivo), 2) AS value
    FROM base_exemplo_score) AS avg
CROSS JOIN
    (SELECT round(stddev(positivo), 2) AS value
    FROM base_exemplo_score) AS stddev;
