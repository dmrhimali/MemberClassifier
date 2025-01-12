-- Insert sample data into Gender table
INSERT INTO gender (type, description) VALUES
('MALE', 'Male Gender'),
('FEMALE', 'Female Gender'),
('OTHER', 'Non-binary Gender');

-- Insert sample data into ChronicCondition table
INSERT INTO chronic_condition (condition, description) VALUES
('HYPERTENSION', 'High blood pressure'),
('DIABETES', 'Chronic condition that affects sugar metabolism'),
('ASTHMA', 'A condition that affects breathing'),
('ARTHRITIS', 'Joint pain and inflammation'),
('CANCER', 'Disease in which cells divide without control'),
('OTHER', 'Any other chronic condition');

-- Insert sample data into GeneralHealthRating table
INSERT INTO general_health_rating (rating, description) VALUES
('EXCELLENT', 'No major health problems'),
('GOOD', 'Occasional minor health issues'),
('FAIR', 'Some health problems affecting quality of life'),
('POOR', 'Multiple chronic conditions or major health issues');

-- Insert sample data into MentalHealthStatus table
INSERT INTO mental_health_status (status, description) VALUES
('COPING_WELL', 'Currently coping well with mental health'),
('UNDER_TREATMENT', 'Receiving treatment for mental health condition'),
('EXPERIENCING_SYMPTOMS', 'Experiencing symptoms of mental health issues');

-- Insert sample data into MentalHealthCondition table
INSERT INTO mental_health_condition (condition, description) VALUES
('DEPRESSION', 'A mental state characterized by persistent feelings of sadness, hopelessness, and a lack of interest in daily activities.'),
('ANXIETY', 'A mental health disorder characterized by feelings of worry, anxiety, or fear that are strong enough to interfere with one''s daily activities.'),
('BIPOLAR_DISORDER', 'A mental health disorder characterized by extreme mood swings that include emotional highs (mania or hypomania) and lows (depression).'),
('SCHIZOPHRENIA', 'A serious mental illness characterized by distorted thinking, perception, emotions, language, sense of self, and behavior.'),
('OTHER', 'Other mental health conditions not specifically categorized.');

-- Insert sample data into StressLevel table
INSERT INTO stress_level (level, description) VALUES
('LOW', 'Low level of stress'),
('MODERATE', 'Moderate level of stress'),
('HIGH', 'High level of stress');

-- Insert sample data into CholesterolLevel table
INSERT INTO cholesterol_level (level, description) VALUES
('NORMAL', 'Normal cholesterol levels'),
('BORDERLINE', 'Borderline cholesterol levels'),
('HIGH', 'High cholesterol levels'),
('VERY_HIGH', 'Very high cholesterol levels');
