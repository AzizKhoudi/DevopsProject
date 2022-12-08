//package tn.esprit.spring;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class ExamThourayaS2ApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
package tn.esprit.spring;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TrainServiceTest {
	
	@Mock
	TrainActiviteRepository TrainRepository;
	
	@InjectMocks
	TrainActiviteServiceImpl TrainService;


	
	@Test
	public void createTrainTest() {
		Train train2 = new Train(null,"124","marketing",null);
		train2.setIdTrain(2L);
		
		TrainService.addTrain(train2);
		verify(TrainRepository, times(1)).save(train2);
		System.out.println(train2); 
		System.out.println("Test 2 : New instance creation - work !");  
	}
	
	
	@Test
	public void getAllTrainsTest() {
		List<Train> TrainList = new ArrayList<Train>() {{
			
			add(new Train(null,"bbbb","gggg",null));
			add(new Train(null,"kkkk","llll",null));
			add(new Train(null,"uuuu","wwww",null));

		}};
		
		when(TrainService.retrieveAllTrain()).thenReturn(TrainList);
		List<Train> sList = TrainService.retrieveAllTrain();
		assertEquals(3, sList.size());
		System.out.println("Test 3 : Retrive all sector instances - work !");
		
	}
	
	@Test
	public void TestDeleteTrain() {
		
		Train train1 = new Train(null,"sec","del",null);
		train1.setIdTrain(7L);
		
		Mockito.lenient().when(TrainRepository.findById(train1.getIdTrain())).thenReturn(Optional.of(train1));
		
		TrainService.deleteTrain(7L);
		verify(TrainRepository).deleteById(train1.getIdTrain());
		
		System.out.println(train1);
		System.out.println("Test 4 : Delete specific sector instance - work !");  
		
	}
	
	
}
