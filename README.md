# Rewards Program

This project calculates reward points for customers based on their transactions. The points are awarded as follows:
- 2 points for every dollar spent over $100 in each transaction.
- 1 point for every dollar spent between $50 and $100 in each transaction.

## Project Structure
- `RewardsApplication.java`: Main application class.
- `Transaction.java`: Model class representing a transaction.
- `RewardService.java`: Service Interface 
- `RewardsServiceImpl.java`: Service implementation class containing the business logic for calculating rewards
- `RewardController.java`: REST controller exposing the endpoint to calculate rewards.
- `RewardRepository.java`: Repository class to connect with H2 database.
- `RewardsControllerTest.java`: Unit tests for the controller.
- `RewardsServiceTest.java`: Unit tests for the service.

## Running the Application

1. Use a tool like Postman to send a POST request to `http://localhost:8081/rewards/calculate` with a JSON body containing customer transaction detail.

2. Use a tool like Postman to send a GET request to `http://localhost:8081/rewards/{CUSTOMER ID}` to get the single customer record

3. Use a tool like Postman to send a GET request to `http://localhost:8081/rewards/{CUSTOMER ID}` to get the all customer transactions month wise

## Testing
- Unit and integration tests are included.
- Run `spring-boot application test classes` to execute the tests.
