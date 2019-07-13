BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `setup` (
	`setupid`	INTEGER,
	`policy`	TEXT,
	`reward`	REAL,
	`punishment`	REAL
);
CREATE TABLE IF NOT EXISTS `session` (
	`setupid`	INTEGER,
	`sessionid`	INTEGER,
	`runid`	INTEGER,
	`player`	TEXT,
	`score`	REAL
);
CREATE TABLE IF NOT EXISTS `playermapping` (
	`setupid`	INTEGER,
	`sessionid`	INTEGER,
	`player`	TEXT,
	`numactions`	INTEGER,
	`numpolicies`	INTEGER
);
COMMIT;
