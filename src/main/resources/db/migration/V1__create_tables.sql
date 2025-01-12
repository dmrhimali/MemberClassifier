-- Create Gender table
CREATE TABLE IF NOT EXISTS gender (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(200) NOT NULL,
    description TEXT
);

-- Create Member table
CREATE TABLE  IF NOT EXISTS member (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    gender_id BIGINT NOT NULL,
    date_of_birth DATE NOT NULL,
    CONSTRAINT fk_gender FOREIGN KEY (gender_id) REFERENCES gender(id)
);

-- Create Address table
CREATE TABLE IF NOT EXISTS  address (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zipcode VARCHAR(20) NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_member_address FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create ChronicCondition table
CREATE TABLE IF NOT EXISTS  chronic_condition (
    id BIGSERIAL PRIMARY KEY,
    condition VARCHAR(200) NOT NULL UNIQUE,
    description TEXT
);

-- Create Contact table
CREATE TABLE IF NOT EXISTS contact (
    id BIGSERIAL PRIMARY KEY,
    contact_type VARCHAR(200) NOT NULL,
    value VARCHAR(255) NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_member_contact FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create GeneralHealthRating table
CREATE TABLE IF NOT EXISTS general_health_rating (
    id BIGSERIAL PRIMARY KEY,
    rating VARCHAR(200) NOT NULL,
    description TEXT
);

-- Create MentalHealthHistory table
CREATE TABLE  IF NOT EXISTS mental_health_condition (
    id BIGSERIAL PRIMARY KEY,
    condition VARCHAR(200) NOT NULL UNIQUE,
    description TEXT
);

-- Create MentalHealthStatus table
CREATE TABLE  IF NOT EXISTS mental_health_status (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(200) NOT NULL,
    description TEXT
);

-- Create StressLevel table
CREATE TABLE  IF NOT EXISTS stress_level (
    id BIGSERIAL PRIMARY KEY,
    level VARCHAR(200) NOT NULL,
    description TEXT
);

-- Create CholesterolLevel table
CREATE TABLE  IF NOT EXISTS cholesterol_level (
    id BIGSERIAL PRIMARY KEY,
    level VARCHAR(200) NOT NULL,
    description TEXT
);

-- Create HealthStatus table
CREATE TABLE  IF NOT EXISTS health_status (
    id BIGSERIAL PRIMARY KEY,
    general_health_rating_id BIGINT NOT NULL,
    mental_health_status_id BIGINT NOT NULL,
    height DOUBLE PRECISION NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    bmi DOUBLE PRECISION NOT NULL,
    blood_pressure VARCHAR(20) NOT NULL,
    heart_rate INT NOT NULL,
    cholesterol_level_id BIGINT NOT NULL,
    stress_level_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_general_health_rating FOREIGN KEY (general_health_rating_id) REFERENCES general_health_rating(id),
    CONSTRAINT fk_mental_health_status FOREIGN KEY (mental_health_status_id) REFERENCES mental_health_status(id),
    CONSTRAINT fk_cholesterol_level FOREIGN KEY (cholesterol_level_id) REFERENCES cholesterol_level(id),
    CONSTRAINT fk_stress_level FOREIGN KEY (stress_level_id) REFERENCES stress_level(id),
    CONSTRAINT fk_member_health_status FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create HealthStatusChronicCondition table
CREATE TABLE health_status_chronic_condition (
    health_status_id BIGINT NOT NULL,
    chronic_condition_id BIGINT NOT NULL,
    PRIMARY KEY (health_status_id, chronic_condition_id),
    CONSTRAINT fk_member FOREIGN KEY (health_status_id) REFERENCES health_status(id) ON DELETE CASCADE,
    CONSTRAINT fk_chronic_condition FOREIGN KEY (chronic_condition_id) REFERENCES chronic_condition(id) ON DELETE CASCADE
);

-- CreateHealthStatusMentalHealthHistory table
CREATE TABLE health_status_mental_health_condition (
    health_status_id BIGINT NOT NULL,
    mental_health_condition_id BIGINT NOT NULL,
    PRIMARY KEY (health_status_id, mental_health_condition_id),
    CONSTRAINT fk_member FOREIGN KEY (health_status_id) REFERENCES health_status(id) ON DELETE CASCADE,
    CONSTRAINT fk_mental_health_condition FOREIGN KEY (mental_health_condition_id) REFERENCES mental_health_condition(id) ON DELETE CASCADE
);

-- Create ClassificationRules table
CREATE TABLE  IF NOT EXISTS classification_rules (
    rule_id BIGSERIAL PRIMARY KEY,
    rule_name VARCHAR(255) NOT NULL,
    classification_definition JSONB -- Using JSONB for better performance with queries
)