package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockSettings;


public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockAStore;
  private TorpedoStore mockBStore;


  @BeforeEach
  public void init(){
    this.mockAStore =  mock(TorpedoStore.class);
    this.mockBStore =  mock(TorpedoStore.class);
    
    this.ship = new GT4500(mockAStore, mockBStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){

    when(mockAStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockAStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){

    when(mockAStore.fire(1)).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockAStore, times(1)).fire(1);
    verify(mockBStore, times(1)).fire(1);
  }

  @Test
  public void test_3feladat_1() {
    when(mockAStore.fire(1)).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore, times(1)).fire(1);
    verify(mockBStore, times(0)).fire(1);

  }

  @Test
  public void test_3feladat_2() {
    when(mockAStore.fire(1)).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore, times(1)).fire(1);
    verify(mockBStore, times(1)).fire(1);

  }

  @Test
  public void test_3feladat_3() {

    when(mockAStore.fire(1)).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore, times(2)).fire(1);
    verify(mockBStore, times(2)).fire(1);
  }

  @Test
  public void test_3feladat_4() {

    when(mockAStore.isEmpty()).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore, times(0)).fire(1);
    verify(mockBStore, times(3)).fire(1);
  }

  @Test
  public void test_3feladat_5() {
    when(mockBStore.fire(1)).thenReturn(true);
    when(mockAStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockAStore, times(2)).fire(1);
    verify(mockBStore, times(2)).fire(1);
  }

  @Test
  public void test_3feladat_6() {
    when(mockAStore.isEmpty()).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockAStore, times(2)).fire(1);
    verify(mockBStore, times(2)).fire(1);

  }

  @Test
  public void test_3feladat_7() {
    //try with another constructor
    
    TorpedoStore mockAStore2 =  mock(TorpedoStore.class, withSettings().useConstructor(0));
    TorpedoStore mockBStore2 =  mock(TorpedoStore.class, withSettings().useConstructor(0));

    when(mockAStore2.isEmpty()).thenReturn(true);
    when(mockBStore2.isEmpty()).thenReturn(true);

    GT4500 shipb = new GT4500(mockAStore2, mockBStore2);

    // Act
    shipb.fireTorpedo(FiringMode.SINGLE);
    shipb.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore2, times(0)).fire(1);
    verify(mockBStore2, times(0)).fire(1);

  }

  @Test
  public void test_3feladat_8() {
    when(mockAStore.fire(1)).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(false);
    
    when(mockAStore.isEmpty()).thenReturn(false);
    when(mockBStore.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore, times(2)).fire(1);
    verify(mockBStore, times(0)).fire(1);

  }
  @Test
  public void test_3feladat_9() {
    when(mockAStore.fire(1)).thenReturn(true);
    when(mockBStore.fire(1)).thenReturn(false);
    
    when(mockAStore.isEmpty()).thenReturn(false);
    when(mockBStore.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    when(mockAStore.isEmpty()).thenReturn(true);

    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockAStore, times(1)).fire(1);
    verify(mockBStore, times(0)).fire(1);

  }

  @Test
  public void test_3feladat_10() {
    boolean result = ship.fireTorpedo(FiringMode.SWDEF);
    assertEquals(false, result);
  }

  @Test
  public void test_3feladat_11() {

    boolean result = ship.fireLaser(FiringMode.SINGLE);
    
    assertEquals(false, result);
  }

}
