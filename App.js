/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View,TouchableOpacity,NativeModules,DeviceEventEmitter,ToastAndroid,TextInput} from 'react-native';
import {createStackNavigator ,NavigationActions} from 'react-navigation';
import RnTest from './RnTest';


const navigateAction = NavigationActions.navigate({ routeName: 'RnTest'});


DeviceEventEmitter.addListener('toRn',(msg)=>{
  title = "React Native界面,收到数据：" + msg;
  ToastAndroid.show(msg, ToastAndroid.SHORT);
  global.navigation.dispatch(navigateAction);
})

class HomeScreen extends Component {
  componentDidMount(){
    global.navigation = this.props.navigation;
  }

  constructor(props) {
		super(props);
      message = '';
      show = '';
  }
  
  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity onPress={() => {
              NativeModules.ReactCommModule.startActivityFromJS("com.rnandroid.SendMessage",this.message);
				}}>
					<Text style={styles.welcome}>
            发送
					</Text>
				</TouchableOpacity>
        <TextInput 
				    underlineColorAndroid={'transparent'}
					  style={[styles.input]}
						multiline={false}
						keyboardType='default'
						placeholder={'未添加'}
						autoFocus={false}
						returnKeyType="next"
            onChangeText={(text) => {
              this.message = text;
            }}
						/>
          <Text>{this.show}</Text>
          <TouchableOpacity
            onPress={()=>{
              this.props.navigation.dispatch(navigateAction);
            }}
          >
          <Text>sd</Text>
          </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  input: {
    paddingBottom: 1,paddingTop:1,
    color: '#000', width: 100,fontSize:15, textAlign: 'center',
},
});

const SimpleApp = createStackNavigator ({
    Home: {
      screen: HomeScreen,
      navigationOptions: {
        title: '主页',
      },
    },//先进入Home类
    RnTest: {
      screen: RnTest,
      navigationOptions: {
        title: 'RN页面',
      },
    },
});

export default SimpleApp;
