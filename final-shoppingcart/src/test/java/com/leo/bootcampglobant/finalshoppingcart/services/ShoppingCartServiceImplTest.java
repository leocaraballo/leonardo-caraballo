package com.leo.bootcampglobant.finalshoppingcart.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leo.bootcampglobant.finalshoppingcart.exceptions.EmptyCartException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.OrderLineNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.ProductAlreadyInCartException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.ProductNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.UserNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.models.CartItem;
import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.models.ShoppingCart;
import com.leo.bootcampglobant.finalshoppingcart.models.User;
import com.leo.bootcampglobant.finalshoppingcart.repositories.CartItemRepository;
import com.leo.bootcampglobant.finalshoppingcart.repositories.ShoppingCartRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShoppingCartServiceImplTest {

  @InjectMocks
  ShoppingCartServiceImpl shoppingCartService;
  @Mock
  ShoppingCartRepository mockedShoppingCartRepository;
  @Mock
  CartItemRepository mockedCartItemRepository;
  @Mock
  ProductService mockedProductService;
  @Mock
  UserService mockedUserService;

  private ShoppingCart testShoppingCart = new ShoppingCart(
      // need to instantiate an ArrayList because Arrays.asList() returns a immutable list.
      new ArrayList<>(Arrays.asList(
          new CartItem(1L, new Product(1L, new Category("TC1", "dtc1"), "Test product 1",
              new BigDecimal("399.99")), 5),
          new CartItem(2L, new Product(2L, new Category("TC2", "dtc2"), "Test product 2",
              new BigDecimal("249.99")), 2),
          new CartItem(3L, new Product(3L, new Category("TC3", "dtc3"), "Test product 3",
              new BigDecimal("175.00")), 1)
      )),
      new User(1L, Collections.emptyList(), "Eamon", "Andrew", "eamondrew", "password",
          "eamondrew@gmail.com")
  );

  @Before
  public void initialize() {
    MockitoAnnotations.initMocks(this);

    when(mockedShoppingCartRepository.findById(anyLong()))
        .thenReturn(Optional.of(testShoppingCart));
  }

  @Test
  public void getShopingCartByUserId_correctRetrieve() {

    assertEquals(testShoppingCart, shoppingCartService.getShopingCartByUserId(1L));
    verify(mockedShoppingCartRepository).findById(1L);
  }

  @Test(expected = UserNotFoundException.class)
  public void getShopingCartByUserId_userNotFound() {
    when(mockedShoppingCartRepository.findById(anyLong())).thenReturn(Optional.empty());
    try {
      shoppingCartService.getShopingCartByUserId(1L);
    } finally {
      verify(mockedShoppingCartRepository).findById(1L);
    }
  }

  @Test
  public void getCartItemById_correctRetrieve() {
    assertEquals(testShoppingCart.getCurrentItems().get(0),
        shoppingCartService.getCartItemById(1L, 1L));
  }

  @Test(expected = OrderLineNotFoundException.class)
  public void getCartItemById_orderLineNotFound() {
    shoppingCartService.getCartItemById(1L, 8L);
  }

  @Test
  public void getCartItemByProductIt_correctRetrieve() {
    assertEquals(testShoppingCart.getCurrentItems().get(0),
        shoppingCartService.getCartItemByProductId(1L, 1L));
  }

  @Test(expected = OrderLineNotFoundException.class)
  public void getCartItemByProductId_orderLineNotFound() {
    shoppingCartService.getCartItemByProductId(1L, 8L);
  }

  @Test
  public void addToCart_correctInsertion() {
    Product insertedProduct = new Product(4L, new Category("TC4", "dtc4"), "Test product 4",
        new BigDecimal("19.99"));
    CartItem insertedOrderLine = new CartItem(4L, insertedProduct, 10);

    when(mockedProductService.getProductById(anyLong())).thenReturn(insertedProduct);
    when(mockedCartItemRepository.save(any())).thenReturn(insertedOrderLine);

    assertEquals(insertedOrderLine, shoppingCartService.addToCart(1L, 4L, 10));
    List<CartItem> items = shoppingCartService.getShopingCartByUserId(1L).getCurrentItems();
    assertEquals(insertedOrderLine, items.get(items.size() - 1));
    verify(mockedShoppingCartRepository).save(any());
  }

  @Test(expected = ProductNotFoundException.class)
  public void addToCart_productNotFound() {
    when(mockedProductService.getProductById(anyLong())).thenThrow(ProductNotFoundException.class);

    shoppingCartService.addToCart(1L, 4L, 10);
  }

  @Test(expected = ProductAlreadyInCartException.class)
  public void addToCart_productAlreadyInCart() {
    Product insertedProduct = testShoppingCart.getCurrentItems().get(0).getProduct();
    when(mockedProductService.getProductById(anyLong()))
        .thenReturn(insertedProduct);

    shoppingCartService.addToCart(1L, 1L, 99);
  }

  @Test
  public void deleteFromCartByCartItemId_correctDeletion() {
    CartItem deletedCartItem = testShoppingCart.getCurrentItems().get(0);
    assertTrue(shoppingCartService.deleteFromCartByCartItemId(1L, 1L));
    assertFalse(shoppingCartService.getShopingCartByUserId(1L).getCurrentItems()
        .contains(deletedCartItem));
    verify(mockedShoppingCartRepository).save(any());
    verify(mockedCartItemRepository).deleteById(1L);
  }

  @Test
  public void deleteFromCartByCartItemId_noOrderLineFound() {
    List<CartItem> originalItems = List.copyOf(testShoppingCart.getCurrentItems());
    assertFalse(shoppingCartService.deleteFromCartByCartItemId(1L, 500L));
    assertEquals(originalItems, shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository, never()).save(any());
    verify(mockedCartItemRepository, never()).deleteById(any());
  }

  @Test
  public void deleteFromCartByProductId_correctDeletion() {
    CartItem deletedCartItem = testShoppingCart.getCurrentItems().get(0);
    assertTrue(shoppingCartService.deleteFromCartByProductId(1L, 1L));
    assertFalse(shoppingCartService.getShopingCartByUserId(1L).getCurrentItems()
        .contains(deletedCartItem));
    verify(mockedShoppingCartRepository).save(any());
    verify(mockedCartItemRepository).delete(deletedCartItem);
  }

  @Test
  public void deleteFromCartByProductId_noOrderLineProductFound() {
    List<CartItem> originalItems = List.copyOf(testShoppingCart.getCurrentItems());
    assertFalse(shoppingCartService.deleteFromCartByProductId(1L, 500L));
    assertEquals(originalItems, shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository, never()).save(any());
    verify(mockedCartItemRepository, never()).delete(any());
  }

  @Test
  public void clear_emptyList() {
    shoppingCartService.clear(1L);
    assertEquals(Collections.emptyList(),
        shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository).save(any());
    verify(mockedCartItemRepository).deleteAll(any());
  }

  @Test
  public void checkOut_emptyListAfter() {
    shoppingCartService.checkOut(1L);
    assertEquals(Collections.emptyList(),
        shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository).save(any());
  }

  @Test
  public void checkOut_correctProducedOrder() {
    Order order = testShoppingCart.produceOrder();
    when(mockedUserService.createOrder(anyLong(), any())).then(AdditionalAnswers.returnsLastArg());

    Order checkOutOrder = shoppingCartService.checkOut(1L);
    assertEquals(order.getTotalCost(), checkOutOrder.getTotalCost());
    assertEquals(new BigDecimal("2674.93"), checkOutOrder.getTotalCost());
    verify(mockedUserService).createOrder(eq(1L), any());
  }

  @Test(expected = EmptyCartException.class)
  public void checkOut_emptyCart() {
    shoppingCartService.clear(1L);
    shoppingCartService.checkOut(1L);
  }

}