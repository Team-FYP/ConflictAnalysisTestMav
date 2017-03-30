DROP DATABASE IF EXISTS conflict_analysis_test_db;
CREATE DATABASE conflict_analysis_test_db;
USE conflict_analysis_test_db;

CREATE TABLE category(category_id INT(4) PRIMARY KEY, 
category_name VARCHAR(50));


CREATE TABLE drug(drug_id INT(4) PRIMARY KEY, 
drug_name VARCHAR(50),
category_id INT(4),
CONSTRAINT FOREIGN KEY drug(category_id) REFERENCES category(category_id) ON UPDATE CASCADE ON DELETE CASCADE);


CREATE TABLE disease(disease_id INT(4) PRIMARY KEY, 
disease_name VARCHAR(50));

CREATE TABLE patient(patient_id INT(4) PRIMARY KEY, 
patient_name VARCHAR(50));

CREATE TABLE graph(graph_id INT(4) PRIMARY KEY);

CREATE TABLE graph_category(
graph_category_id INT(4),
graph_id INT(4), 
category_id INT(4),
CONSTRAINT PRIMARY KEY (graph_category_id),
CONSTRAINT FOREIGN KEY graph_category(graph_id) REFERENCES graph(graph_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY graph_category1(category_id) REFERENCES category(category_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE successor(
graph_category_id INT(4), 
successor_id INT(4),
CONSTRAINT PRIMARY KEY (graph_category_id, successor_id),
CONSTRAINT FOREIGN KEY successor(graph_category_id) REFERENCES graph_category(graph_category_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY successor1(successor_id) REFERENCES category(category_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE predecessor(
graph_category_id INT(4), 
predecessor_id INT(4),
CONSTRAINT PRIMARY KEY (graph_category_id, predecessor_id),
CONSTRAINT FOREIGN KEY predecessor(graph_category_id) REFERENCES graph_category(graph_category_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY predecessor1(predecessor_id) REFERENCES category(category_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE d_d_interaction(drug1_id INT(4), 
drug2_id INT(4),
severity INT(4),
CONSTRAINT PRIMARY KEY (drug1_id, drug2_id),
CONSTRAINT FOREIGN KEY d_d_interaction(drug1_id) REFERENCES drug(drug_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY d_d_interaction2(drug2_id) REFERENCES drug(drug_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE d_di_interaction(drug_id INT(4), 
disease_id INT(4),
severity INT(4),
CONSTRAINT PRIMARY KEY (drug_id, disease_id),
CONSTRAINT FOREIGN KEY d_di_interaction(drug_id) REFERENCES drug(drug_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY d_di_interaction2(disease_id) REFERENCES disease(disease_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE d_p_interaction(drug_id INT(4), 
patient_id INT(4),
severity INT(4),
CONSTRAINT PRIMARY KEY (drug_id, patient_id),
CONSTRAINT FOREIGN KEY d_p_interaction(drug_id) REFERENCES drug(drug_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY d_p_interaction2(patient_id) REFERENCES patient(patient_id) ON UPDATE CASCADE ON DELETE CASCADE);