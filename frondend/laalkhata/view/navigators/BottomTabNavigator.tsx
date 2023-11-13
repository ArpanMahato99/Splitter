import { Pressable, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import ProfileScreen from '../screens/ProfileScreen';
import { styles as appStyles} from '../../styles';
import FriendsStackNavigation from './FriendsStackNavigation';
import ActivityStackNavigator from './ActivityStackNavigator';
import {FontAwesomeIcon} from '@fortawesome/react-native-fontawesome'
import {faUserFriends, faMoneyBillTransfer, faUser} from '@fortawesome/free-solid-svg-icons'

const Tab = createBottomTabNavigator();

export default function BottomTabNavigator() {
  return (
    <Tab.Navigator
        screenOptions={{
            headerShown: false,
            headerStyle: {...styles.headerStyle},
            headerTitleStyle: {...styles.headerTitle},
            tabBarShowLabel: false,
            tabBarActiveTintColor: "#2ecc72",
            tabBarInactiveTintColor: "#DAE0E2",
            tabBarStyle: {...styles.tabBarStyle},
        }}
        sceneContainerStyle={appStyles.darkAppContainer}      
    >
        <Tab.Screen 
            name="Friends" 
            component={FriendsStackNavigation}
            options={{
                tabBarIcon: ({color}) => (
                    <View>
                        <FontAwesomeIcon 
                            icon={faUserFriends} 
                            color={color} 
                            size={30}
                        />
                    </View>
                    
                )
            }} 
        />
        <Tab.Screen 
            name="ActivityNavigator" 
            component={ActivityStackNavigator}
            options={{
                tabBarIcon: ({color}) => (
                    <View>
                        <FontAwesomeIcon
                            icon={faMoneyBillTransfer}
                            color={color} 
                            size={30} />
                    </View>
                )
            }}
        />
        <Tab.Screen 
            name="Profile" 
            component={ProfileScreen}
            options={{
                tabBarIcon: ({color}) => (
                    <View>
                        <FontAwesomeIcon icon={faUser} color={color} size={25} />
                    </View>
                )
            }}
        />
    </Tab.Navigator>
  )
}

const styles = StyleSheet.create({
    tabBarStyle: {
        backgroundColor: "#192a56",
        elevation: 0,    
        height: 70,
        paddingBottom: 5,
        paddingTop: 5,
        marginBottom: 20,
        marginHorizontal: 10,
        borderRadius: 20,
        borderColor: "#192a56"
    },
    navText: {
        fontWeight: 'bold',
        fontSize: 12,
    },
    headerStyle: {
        backgroundColor: "#000",
    },
    headerIcon: {
        marginHorizontal: 15,
        color: '#ffffff',
        fontSize: 20
    },
    headerTitle: {
        color: "#ffffff",
        fontStyle: 'italic',
        fontWeight: '100',
    },
})