//
//  SermonsView.swift
//  MomentumiOS
//
//  Created by Mwai Banda on 9/19/22.
//  Copyright © 2022 Momentum. All rights reserved.
//

import SwiftUI
import AVKit
import MomentumSDK
import Combine

struct SermonsView: View {
    @StateObject private var sermonViewmodel = SermonsViewModel()
    @State private var sermon: Sermon? = nil
    @State private var showSermon = false
    
    @State private var showSearch = false
    @State private var showFilter = false
    @State private var filtered = [Sermon]()
    @State private var disposables = Set<AnyCancellable>()
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    var body: some View {
        
        VStack(spacing: 0){
            
            Divider()
            if !showSearch && !showFilter {
                HStack {
                    Text("TAP TO WATCH RECENT SERMONS")
                        .font(.caption2)
                        .foregroundColor(Color(hex: Constants.MOMENTUM_ORANGE))
                        .padding(.leading)
                        .padding(.leading, 5)
                    
                    Spacer()
                }
                .padding(.vertical, 5)
            } else if showFilter {
                HStack {
                    Text("FILTER SERMONS")
                        .font(.caption2)
                        .foregroundColor(Color(hex: Constants.MOMENTUM_ORANGE))
                        .padding(.leading)
                        .padding(.leading, 5)
                    
                    Spacer()
                }
                .padding(.top, 5)
            }
          
            
            
            ScrollView(showsIndicators: false) {
                VStack(alignment: .leading) {
                    VStack(alignment: .leading) {
                        
                        if showSearch {
                            TextField("Search \(sermonViewmodel.searchTag)", text: $sermonViewmodel.searchTerm)
                                .padding(.top, 5)
                            
                        }
                        if showFilter {
                            
                            Button{
                                sermonViewmodel.filterFavourites = !sermonViewmodel.filterFavourites
                                sermonViewmodel.filterOldest = false
                                sermonViewmodel.filterNewest = false
                            } label: {
                                HStack {
                                    Image(systemName:  sermonViewmodel.filterFavourites ? "checkmark.square.fill" : "square")
                                        .imageScale(.medium)
                                        .foregroundColor(sermonViewmodel.filterFavourites ? .init(hex: Constants.MOMENTUM_ORANGE) : .black)
                                    Text("Favourites")
                                        .foregroundColor(.black)
                                }
                            }.contentShape(Rectangle())
                            
                            Button{
                                sermonViewmodel.filterNewest = !sermonViewmodel.filterNewest
                                sermonViewmodel.filterOldest = false
                                sermonViewmodel.filterFavourites = false
                            } label: {
                                HStack {
                                    Image(systemName:  sermonViewmodel.filterNewest ? "checkmark.square.fill" : "square")
                                        .imageScale(.medium)
                                        .foregroundColor(sermonViewmodel.filterNewest ? .init(hex: Constants.MOMENTUM_ORANGE) : .black)
                                    Text("Newest")
                                        .foregroundColor(.black)
                                }
                            }.contentShape(Rectangle())
                            
                            
                            Button{
                                sermonViewmodel.filterOldest = !sermonViewmodel.filterOldest
                                sermonViewmodel.filterNewest = false
                                sermonViewmodel.filterFavourites = false
                            } label: {
                                HStack {
                                    Image(systemName:  sermonViewmodel.filterOldest ? "checkmark.square.fill" : "square")
                                        .imageScale(.medium)
                                        .foregroundColor(sermonViewmodel.filterOldest ? .init(hex: Constants.MOMENTUM_ORANGE) : .black)
                                    Text("Oldest")
                                        .foregroundColor(.black)
                                }
                            }.contentShape(Rectangle())
                        }
                    }
                    .font(.title3)
                    .padding(.horizontal)
                    .padding(.horizontal, 5)
                    if showSearch || showFilter {
                        Divider()
                            .padding(.top, 5)
                    }
                }
                .frame(height: showSearch ? 50 : showFilter ? 130 : 0)
                .padding(.bottom, 5)
                LazyVGrid(columns: columns, spacing: 10) {
                    if sermonViewmodel.sermons.isEmpty {
                        ForEach(0..<12, id: \.self
                        ) { _ in
                            SermonCard(
                                isRedacted: true,
                                sermon: Sermon(
                                    id: "\(Int.random(in: 1000..<9999))",
                                    series: "14:7 Refresh - Part 1",
                                    title: "Pre-Decide: Better Choices, Better Life",
                                    preacher: "Charlie Arms",
                                    videoThumbnail: "thumbnail",
                                    videoURL: "",
                                    date: "Oct 2, 2022",
                                    dateMillis: Int64(0)
                                ),
                                playedSermons: sermonViewmodel.watchedSermons
                            )
                            .frame(maxHeight: 250)
                            
                        }
                    } else {
                        ForEach(filtered, id: \.id) { sermon in
                            SermonCard(
                                sermon: sermon,
                                playedSermons: sermonViewmodel.watchedSermons,
                                favourite: sermonViewmodel.favourites.first(where: { $0.id == sermon.id }) != nil,
                                onFavouriteClick: { isFavourite in
                                    if isFavourite {
                                        sermonViewmodel.addFavouriteSermon(id: sermon.id)
                                    } else {
                                        sermonViewmodel.removeFavouriteSermon(id: sermon.id)
                                    }
                                }
                            ) {
                                self.sermon = $0
                                showSermon = true
                                print("clicked")
                            }
                            .frame(maxHeight: UIDevice.isIPad ? 350 : 250)
                        }
                    }
                }
                .padding(.horizontal, 10)
                .padding(.bottom, sermonViewmodel.canLoadMoreSermons ? 25 :  50)
                .padding(.bottom, 10)
                if !sermonViewmodel.sermons.isEmpty {
                    if sermonViewmodel.canLoadMoreSermons {
                        Button { sermonViewmodel.loadMoreSermons() } label: {
                            Text("Load More")
                                .padding(.horizontal, 22)
                                .padding(.vertical, 10)
                        }
                        .buttonStyle(FilledButtonStyle())
                        .padding(.bottom, 25)
                    }
                }
            }
            .redacted(reason: sermonViewmodel.sermons.isEmpty ? .placeholder : [])
            Divider()
                .padding(.bottom, 5)
            
        }
        .background(Color.white.edgesIgnoringSafeArea(.bottom))
        .navigationBarTitleDisplayMode(.inline)
        .toolbar(content: {
            
            ToolbarItemGroup(placement: .navigationBarLeading) {
                Text("Sermons")
                    .font(.title3)
                    .fontWeight(.heavy)
                    .background(Color.white.edgesIgnoringSafeArea(.top))
            }
            
            ToolbarItemGroup(placement: .navigationBarTrailing) {
                HStack {
                    Button {
                        withAnimation(.easeInOut(duration: 0.35)) {
                            showSearch.toggle()
                            showFilter = false
                            
                        }
                        
                    } label: {
                        Image(systemName: showSearch ? "xmark" : "magnifyingglass")
                    }
                    Button {
                        withAnimation(.easeInOut(duration: 0.35)) {
                            showFilter.toggle()
                            showSearch = false
                        }
                    } label: {
                        Image(systemName: showFilter ? "xmark" : "line.3.horizontal")
                    }
                }
            }
        })
        .onAppear {
            DispatchQueue.main.async {
                sermonViewmodel.fetchSermons()
                sermonViewmodel.getWatchedSermons {
                    sermonViewmodel.getFavouriteSermons()
                }
                sermonViewmodel.filteredSermons.sink { sermons in
                    if !sermonViewmodel.filterNewest || !sermonViewmodel.filterOldest {
                        filtered = sermons
                    }
                }.store(in: &disposables)
                Timer.publish(every: 2.5, on: .main, in: .common)
                    .autoconnect()
                    .prepend(Date())
                    .map { _ in }
                    .sink(receiveValue: { _ in
                        withAnimation {
                            sermonViewmodel.shiftSearchTag()
                        }
                    })
                    .store(in: &disposables)
                sermonViewmodel.sortedSermons.sink { sermons in
                    if sermonViewmodel.filterNewest || sermonViewmodel.filterOldest {
                        filtered = sermons
                    }
                }.store(in: &disposables)
            }
        }
        .fullScreenCover(item: $sermon) { sermon in
            ZStack {
                Color.black.ignoresSafeArea(.all)
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: Color(hex: Constants.MOMENTUM_ORANGE)))
                PlayerView(
                    player: sermonViewmodel.player,
                    playbackURL: sermon.videoURL,
                    lastPlayedTime: {
                        let lastPlayedSermon = sermonViewmodel.watchedSermons.first(where: { $0.id == sermon.id })
                        let myTime = CMTime(seconds: lastPlayedSermon?.last_played_time ?? 0, preferredTimescale: 60000)
                        return myTime
                    }
                ).ignoresSafeArea(.all)

            }
            .onDisappear {
                if   #available(iOS 16.0, *) {
                    let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene
                    windowScene?.requestGeometryUpdate(.iOS(interfaceOrientations: .portrait))
                    
                } else {
                    UIDevice.current.setValue(UIInterfaceOrientation.portrait.rawValue, forKey: "orientation")
                }
                
                
                AppDelegate.orientationLock = .portrait
                let currentTimeInSeconds: Double = sermonViewmodel.player.currentItem?.currentTime().seconds ?? 0
                let currentTimeInSecondsRounded = round(currentTimeInSeconds * 100) / 100
                let duration = (round((sermonViewmodel.player.currentItem?.duration.seconds ?? 0) * 100) / 100)
                let playedPercentage =  Int32((currentTimeInSecondsRounded / duration) * 100)
                print("[CurrentTimeInSeconds]: \(currentTimeInSeconds)")
                print("[Duration]: \(duration)")
                print("[PlayedPercentage]: \(playedPercentage)% watched")

                sermonViewmodel.addSermon(
                    sermon: MomentumSermon(
                        id: sermonViewmodel.currentSermon?.id ?? "",
                        last_played_time: currentTimeInSecondsRounded,
                        last_played_percentage: playedPercentage
                    )
                )
                sermonViewmodel.resetNowPlaying()
            }
            .onAppear {
                AppDelegate.orientationLock = .all
                sermonViewmodel.updateNowPlaying(sermon: sermon)
                sermonViewmodel.currentSermon = sermon
                
            }
        }
    }
}


struct SermonsView_Previews: PreviewProvider {
    static var previews: some View {
        SermonsView()
    }
}



